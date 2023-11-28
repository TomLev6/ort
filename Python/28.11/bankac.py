import random
import threading
from collections import Counter


class BankAccount:
    def __init__(self, name, balance):
        self.balance = balance
        self.lock = threading.Lock()

    def get_balance(self) -> Counter:
        return self.balance['1']

    def set_balance(self, newbalance):
        self.balance = newbalance

    def withdraw(self, amount) -> bool:
        if self.balance['1'] < amount:
            return False

        self.balance.subtract(['1'] * amount)
        return True

    def deposit(self, amount):
        self.balance.update(['1'] * amount)


b = BankAccount("Tom", 20000)


def do_dep(ba):
    for _ in range(100):
        b.deposit(random.randint(500, 1000))


def do_wtd(ba):
    for _ in range(100):
        b.withdraw(random.randint(500, 1000))


thread1 = threading.Thread(target=do_dep, args=(b,))
thread2 = threading.Thread(target=do_wtd, args=(b,))
thread1.start()
thread2.start()

print(b.balance.total())
