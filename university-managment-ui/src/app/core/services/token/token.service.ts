import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  public set token(token: string) {
    console.log("the erroro is ")
    localStorage.setItem("token", token);
  }

  get token(): string | null {
    return localStorage.getItem("token")
  }

  constructor() {
  }
}
