import time
import spidev
import sys

def spi_init():
    spi = spidev.SpiDev()
    spi.open(0,0)
    spi.max_speed_hz  = 937500


def send_data(data):
    val = int(data, 16)
    data = [val]
    print(data)
    for i in data:
        # time.sleep(1)
        spi.xfer([i])


def receive_data():
    # time.sleep(0.05)
    val = "".join([chr(int(str(i), 0)) for i in spi.readbytes(1)])
    return val

def spi_action(count,code,value):
    if count > 0:
        spi_init()
        if (code == "h"):
            send_data(value)
        elif (code == "l"):
            temp = value
            print(temp)
            print(len(temp))

            data = [len(temp)]
            for i in data:
                print(i)
                spi.xfer([i])
        else:
            temp = value
            for i in temp:
                data = [ord(i)]
                for i in data:
                    print(i)
                    spi.xfer([i])
        return count-1
    return count


def get_state():
    with open("state.txt","r") as fp:
        return fp.readline()

def enable_swipe():
    val = True
    with open("credit_card.txt","r") as fp2:
        if fp2.readline() != "":
            val = False
    if val:
        inp = None

        while not inp:
            inp = input("Swipe card")

        print(inp)

        if inp[0:2] != "%B":
            with open("credit_card.txt", "w") as fp:
                fp.write("FALSE")
        else:
            # Credit or debit card
            print("its a credit ")
            imp = inp.split('^')

            cr_no = imp[0][2:]
            cr_name = " ".join(imp[1].strip().split('/')[::-1])
            cr_exp = imp[2][2:4] + "/" + imp[2][:2]

            with open("credit_card.txt", "w") as fp:
                fp.write(cr_no + "\n" + cr_name + "\n" + cr_exp)


if __name__ == '__main__':
    #enable_swipe()
    with open("credit_card.txt", "w") as fp:
        fp.write("")

    countI = 1
    countL = 1
    countS = 1
    countM = 1
    countC = 1
    countSW = 1
    countEC = 1
    countE = 1
    countEN = 1

    while True:
        curr_state = get_state()
        if curr_state == "IDLE":
            pass
        elif curr_state == "LOGIN":
            if countL == 1:
                countL = spi_action(countI, "h", "0x04")
        elif curr_state == "SIGN_UP":
            if countS == 1:
                countS = spi_action(countI, "h", "0x04")
        elif curr_state == "MAIN":
            if countM == 1:
                countM = spi_action(countI, "h", "0x03")
        elif curr_state == "CHECK_OUT":
            if countC == 1:
                countC = spi_action(countI, "h", "0x02")
        elif curr_state == "SWIPE_CARD":
            if countSW == 1:
                countSW = spi_action(countI, "h", "0x01")
            enable_swipe()
        elif curr_state == "ENTER_CARD":
            if countEC == 1:
                countEC = spi_action(countI, "h", "0x05")
        elif curr_state == "EDIT":
            if countE == 1:
                countE = spi_action(countI, "h", "0x07")
        elif curr_state == "END":
            if countEN == 1:
                countEN = spi_action(countI, "h", "0x08")

            countI = 1
            countL = 1
            countS = 1
            countM = 1
            countC = 1
            countSW = 1
            countEC = 1
            countE = 1
            countEN = 1
            with open("credit_card.txt", "w") as fp:
                fp.write("")





