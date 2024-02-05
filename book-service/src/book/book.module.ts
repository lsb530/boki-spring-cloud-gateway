import { Module } from '@nestjs/common'
import { BookController } from '@/book/book.controller'
import { ApiKeyGuard } from '@/guard/ApiKeyGuard'
import { BookService } from '@/book/book.service'

@Module({
  controllers: [BookController],
  providers: [ApiKeyGuard, BookService],
})
export class BookModule {}
