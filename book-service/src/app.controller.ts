import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Get('/rest/v2/api/books')
  getBooks(): any {
    return [
      {
        id: 1,
        name: 'Harry Porter',
      },
      {
        id: 2,
        name: 'Slam Dunk',
      },
    ];
  }
}
