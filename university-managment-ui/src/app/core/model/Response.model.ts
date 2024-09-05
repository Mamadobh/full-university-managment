import {ExceptionResponseModel} from "./ExceptionResponse.model";

export interface Response<T> {
  success: boolean;
  status: string
  data?: T
  error?: ExceptionResponseModel;
}
