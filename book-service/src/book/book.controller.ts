import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post,
  UseGuards,
} from '@nestjs/common'
import { ApiKeyGuard } from '@/guard/ApiKeyGuard'
import { BookService } from '@/book/book.service'
import BookDto from '@/book/dto/book.dto'
import UpdateBookDto from '@/book/dto/update-book.dto'
import CreateBookDto from '@/book/dto/create-book.dto'

@UseGuards(ApiKeyGuard)
@Controller('/rest/v2/api/books')
export class BookController {
  constructor(private readonly bookService: BookService) {}

  @Get()
  getBooks(): BookDto[] {
    return this.bookService.findAll()
  }

  @Get(':id')
  getBook(@Param('id') id: string): BookDto {
    return this.bookService.findOne(+id)
  }

  @Post()
  createBook(@Body() req: CreateBookDto): BookDto {
    return this.bookService.create(req)
  }

  @Patch(':id')
  updateBook(@Param('id') id: string, @Body() req: UpdateBookDto): BookDto {
    return this.bookService.update(+id, req)
  }

  @Delete(':id')
  deleteBook(@Param('id') id: string) {
    return this.bookService.delete(+id)
  }
}
