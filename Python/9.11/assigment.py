class Book:
    def __init__(self):
        self.book_name = ""
        self.copies = 0
        self.price = 40

    def get_book_name(self):
        return self.book_name

    def get_price(self):
        return self.price

    def get_copies(self):
        return self.copies

    def set_name(self,name):
        self.book_name = name

    def set_price(self, price):
        self.price = price

    def set_copies(self, number):
        self.copies = number

    def cost(self):
        return self.copies * self.price

    def __str__(self):
        return f"{self.book_name}, price: {self.price}, copies: {self.copies}"


class Libery:
    def __init__(self):
        self.book_list = []

    def __str__(self):
        return [book for book in self.book_list]

    def add(self, book_name, price):
        b = Book()
        b.set_name(book_name)
        b.set_price(price)
        for book in self.book_list:
            if book_name == book.get_book_name():
                book.set_copies(book.get_copies()+1)
            else:
                self.book_list.append(b)

    def sum_copies(self):
        total = 0
        for book in self.book_list:
            total += book.get_copies()
        return total

    def low_copies(self, copies_amount):
        return [book.get_book_name() for book in self.book_list if book.get_copies() < copies_amount]

                 