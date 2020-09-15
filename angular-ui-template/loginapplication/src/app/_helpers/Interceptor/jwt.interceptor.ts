import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const headerObj = { setHeaders: {} };
        headerObj.setHeaders['Access-Control-Allow-Origin'], '*';
        headerObj.setHeaders['Access-Control-Allow-Credentials'], 'true';
        headerObj.setHeaders['Access-Control-Allow-Methods'], 'GET,HEAD,OPTIONS,POST,PUT';
        headerObj.setHeaders['Access-Control-Allow-Headers'], 'Origin, X-Requested-With, Content-Type, Accept';
        request = request.clone(headerObj);
        return next.handle(request);
    }
}
