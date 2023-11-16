import threading
import time
import random

distance = []
for i in range(10):
    distance.append(0)


def simulate_runner(index):
    global distance
    covered_distance = 0
    distance[index] = covered_distance
    while True:
        covered_distance += 10
        time.sleep(random.randint(1, 5))
        distance[index] += covered_distance


def main():
    global distance
    runners = []
    for n in range(10):
        t = threading.Thread(target=simulate_runner, args=(n,))
        runners.append(t)
        t.start()
    time.sleep(10)
    # distance_copy = []
    # distance_copy = distance.copy()
    val = max([value for value in distance])
    print(distance.index(val), ":", val)
    exit()


if __name__ == '__main__':
    main()
