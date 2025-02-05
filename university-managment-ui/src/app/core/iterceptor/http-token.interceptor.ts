import {HttpInterceptorFn} from '@angular/common/http';
import {inject} from "@angular/core";
import {TokenService} from "../services/token/token.service";

export const httpTokenInterceptor: HttpInterceptorFn = (req, next) => {

  const tokenService = inject(TokenService)
  if (typeof window !== 'undefined' && window.localStorage) {
    const authToken = tokenService.token;
    console.log("token ",authToken)
    if (authToken) {
      const authReq = req.clone({
        headers: req.headers.append("Authorization", "Bearer " + authToken)
      })
      return next(authReq)
    }  }

  return next(req);
};
