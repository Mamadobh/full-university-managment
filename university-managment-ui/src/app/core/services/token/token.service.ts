import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  public set token(token: string) {
    localStorage.setItem("token", token);
  }

  get token(): string | null {
    if (typeof window !== 'undefined' && window.localStorage) {
      return localStorage.getItem("token")

    }
    return null;
  }

  isTokenNotValid() {
    return this.isTokenExpired()
  }

  private isTokenExpired(): boolean {

    if (!this.token) {
      return true; // No token found, considered expired
    }

    const parts = this.token.split('.'); // JWT has 3 parts (header, payload, signature)
    if (parts.length !== 3) {
      return true; // Invalid token format
    }

    try {
      const payload = JSON.parse(atob(parts[1])); // Decode the payload
      const currentTime = Date.now() / 1000; // Current time in seconds
      return payload.exp < currentTime; // Check if the token is expired
    } catch (error) {
      console.error('Error decoding token', error);
      return true;
    }


  }
}
