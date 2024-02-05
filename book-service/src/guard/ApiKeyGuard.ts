import {
  Injectable,
  CanActivate,
  ExecutionContext,
  HttpException,
  HttpStatus,
} from '@nestjs/common'
import { ConfigService } from '@nestjs/config'

@Injectable()
export class ApiKeyGuard implements CanActivate {
  apiKey: string
  constructor(private configService: ConfigService) {
    this.apiKey = configService.getOrThrow('API_KEY')
  }
  canActivate(context: ExecutionContext): boolean {
    const request = context.switchToHttp().getRequest()
    const apiKey = request.headers['api-key']

    if (apiKey === this.apiKey) {
      return true
    } else {
      throw new HttpException('Unauthorized', HttpStatus.UNAUTHORIZED)
    }
  }
}
