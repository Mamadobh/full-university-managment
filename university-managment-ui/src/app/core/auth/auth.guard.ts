import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {TokenService} from "../services/token/token.service";

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(TokenService)
  const router = inject(Router)
  if(!authService.token && typeof  window == 'undefined' ){
    return false
  }
  if (typeof window !== 'undefined' && window.localStorage && authService.isTokenNotValid()) {
    router.navigate(["back-office","auth"])
    return false;
  } else {
    return true
  }
};
export const protectAdminLoginGuard: CanActivateFn = (route, state) => {
  const authService = inject(TokenService)
  const router = inject(Router)
  if(!authService.token && typeof  window == 'undefined' ){
    return false
  }
  if (authService.token ) {
    router.navigate(["back-office/views/dashboard"])
    return false;
  } else {
    return true
  }
};
