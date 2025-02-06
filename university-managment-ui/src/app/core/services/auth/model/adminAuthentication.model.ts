export class AuthenticationRequest{

  constructor(public password: string, public email: string) {
  }
}

export class AdminAuthenticationResponse {
  constructor(public id: number,
              public token: string,
              public resources: Map<string, Set<string>>,
              public roles: Set<String>,
              public sideMenu: Set<String>
  ) {
  }
}


