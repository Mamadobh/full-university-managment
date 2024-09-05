import {error} from "@angular/compiler-cli/src/transformers/util";

export class ExceptionResponse{
  businessErrorCode?: number;
  error?: string;
  errors?: { [key: string]: string };

  constructor(private businessErrorCode: number, error: string, errors: { [p: string]: string }) {

  }
}
