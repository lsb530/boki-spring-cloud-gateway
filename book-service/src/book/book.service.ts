import { Injectable, NotFoundException } from '@nestjs/common'
import BookDto from '@/book/dto/book.dto'
import UpdateBookDto from '@/book/dto/update-book.dto'
import CreateBookDto from '@/book/dto/create-book.dto'

@Injectable()
export class BookService {
  private books: BookDto[] = [
    {
      id: 1,
      name: 'Harry Porter',
    },
    {
      id: 2,
      name: 'Slam Dunk',
    },
  ]

  findAll() {
    return this.books
  }

  findOne(id: number) {
    return this.validateExistsAndGet(id)
  }

  create(req: CreateBookDto) {
    const newBookId =
      this.books.length > 0
        ? Math.max(...this.books.map((book) => book.id)) + 1
        : 1
    const newBook: BookDto = {
      id: newBookId,
      name: req.name,
    }
    this.books.push(newBook)
    return newBook
  }

  update(id: number, req: UpdateBookDto) {
    const findBook = this.validateExistsAndGet(id)
    findBook.name = req.name
    return findBook
  }

  delete(id: number) {
    const findBook = this.validateExistsAndGet(id)
    this.books = this.books.filter((book) => book.id !== findBook.id)
    return this.books
  }

  private validateExistsAndGet(id: number) {
    const book = this.books.find((book) => book.id === id)
    if (!book) {
      throw new NotFoundException(`Book is not found`)
    }
    return book
  }
}
