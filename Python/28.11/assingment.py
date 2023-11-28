import threading
counter = 0
lock = threading.Lock()


def increment():
    global counter, lock
    lock.acquire()
    for _ in range(1_000_000):
        counter += 1
    lock.release()


def decrement():
    global counter, lock
    lock.acquire()
    for _ in range(1_000_000):
        counter -= 1
    lock.release()


thread1 = threading.Thread(target=increment)
thread2 = threading.Thread(target=decrement)
thread1.start()
thread2.start()
thread1.join()
thread2.join()

print(f"Final counter value: {counter}")
print("\n")

from collections import Counter
counter = Counter()


def increment():
    global counter, lock
    for _ in range(1_000_000):
        counter.update(['1'])


def decrement():
    global counter
    for _ in range(1_000_000):
        del counter['1']


thread1 = threading.Thread(target=increment)
thread2 = threading.Thread(target=decrement)
thread1.start()
thread2.start()

print(f"Final counter value: {counter['1']}")


