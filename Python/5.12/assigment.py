def max_char(string):
    string = string.split()
    string = sorted(string, key=len)
    max_count = 0
    max_c = ''
    i = 0
    for word in string:
        if word.count(word[i]) > max_count:
            max_count = word.count(word[i])
            max_c = word[i]
        i += 1

    return max_c


def create_anagram_dic():
    lis_of_strings = ["bad", "word", "dab", "rowd", "word"]
    dic = {}
    lis = ["",]
    i = 0
    for word in lis_of_strings:
        for char in word:
            if char not in lis[i]:
                lis[i] += char  # error out of index!!!
        i += 1
    i = 0
    for words in lis:
        if sorted(words) not in sorted(dic.values()):
            dic[str(i)] = words
        else:
            dic.update({f"{str(i)}: {dic.get(sorted(words)) + words}"})

    return dic


dt = create_anagram_dic()
print(dt.items())
