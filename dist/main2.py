import os
from getpass import getpass

def send_data(data):
    os.system("python spitest1.py " + data)

def read_whisper():
    with open("stm_whisper.txt") as file:
        return file.readline()


state = "IDLE"
prev_state = "IDLE"
if __name__ == '__main__':
    while True:
        if state == "IDLE":
            inp = input("Welcome to the SmartKart!\nChoose to Login or Singup: ")
            prev_state = state
            if inp == "Login":
                state = "LOGIN"
                send_data("h 0x04")
            elif inp == "Signup":
                state = "SIGNUP"
                send_data("h 0x02")

        elif state == "LOGIN":

            user = input("Enter username: ")
            password = getpass("Enter a password: ")
            i = input("press login")

            if i != "":
                send_data("h 0x03")
                send_data("s "+ user + ","+ password )


            #TODO Authenticate
            # if authenticated:
            #   state = "MAIN_MENU"
            # else:
            #   print("wrong username or password")
            prev_state = state

        elif state == "SIGNUP":
            user = input("Enter username: ")
            password = input("Enter a password: ")
            budget = input("Enter a budget: ")
            print("Welcome to the SmartKart Family")
            prev_state = state
            state = "MAIN_MENU"
        elif state == "MAIN_MENU":
            if prev_state != state:
                print("scanning your items, press c to checkout, e to edit budget")
            prev_state = state
            if input() == "c":
                state = "CHECKOUT"
            elif input() == "e":
                state = "EDIT"
            #TODO
        elif state == "EDIT":
            budget = input("Re-enter a budget: ")
            prev_state = state
            state = "MAIN_MENU"
        elif state == "CHECKOUT":
            np = input("Choose to Swipe or Enter card:")
            prev_state = state
            if np == "Swipe card":
                state = "SWIPE_CARD"
            elif np == "Enter Card":
                state = "ENTER_CARD"
        elif state == "ENTER_CARD":
            card = input("Enter a card: ")
            prev_state = state
            state = "IDLE"
        elif state == "SWIPE_CARD":
            print("swipe your card")
            prev_state = state
            #reading card val
            state = "IDLE"

