def prime(num, min, max):
    count = 0
    for i in range(min, max):
        if num % i == 0:
            count += 1
            if count > 2:
                return False
    return True


def q1(min=1, max=100):
    l = []
    for x in range(2, max):
        if prime(x, min=min, max=max):
            if len(l) < 4:
                l.append(x)
            else:
                break
    return tuple(l)


def func1():
    tup = q1()
    first, second, third, four = tup
    print(first, second, third, four)


def func2(tup1, tup2):
    tup3 = []
    for i in tup1:
        if prime(i, 101, 200):
            tup3.append(i)
    for i in tup2:
        if prime(i, 201, 300):
            tup3.append(i)
    tup3.append(997)
    index = len(tup3) / 2
    tup3.pop(int(index))
    tup3 = tuple(tup3)
    print(tup3)


tup1 = (163,121,133,107)
tup2 = (203,281,220,287)
func2(tup1,tup2)


def secret(encrypted_msg):
    dictionary = {
        "a": "b",
        "c": "a",
        "g": "d",
        "s": "x",
        "e": "r",
        "l": "o",
        "o": "p",
        "k": "l",
        "p": "k",
        "y": "u",
        "n": "m",
    }
    decrypted_msg = ""
    for ch in encrypted_msg:
        decrypted_msg.join(dictionary.get(ch))
    return decrypted_msg


def question2(dictionary):
    passed = {}
    failed = {}
    for k, v in dictionary:
        if dictionary.get(k) > 54:
            passed[k] = v
        else:
            failed[k] = v
    for key in failed.keys():
        failed.update({key+":"+failed[key]+20})
    total = {}
    for k, v in passed:
        total[k] = v
    for k, v in failed:
        total[k] = v
    # total = {i: total[i] for i in total.keys() if lambda i: i+1}
    total = dict(sorted(total.values(), key=lambda val: val[1]))
    print(total)
    total.popitem()
    min_num = -1
    for k in total:
        if total[k] < min_num:
            min_num = k
    total.pop(min_num)
    return total


def count_letters(string):
    string = string.lower()
    string = list(string)
    string.sort()
    string = str(string)
    for ch in string:
        print(string.count(ch), ch)


def unique(dictionary):
    s = set()
    for k, v in dictionary:
        s.add(v)
    print(s)
