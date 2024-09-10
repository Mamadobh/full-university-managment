import {ExceptionResponseModel} from "./ExceptionResponse.model";

export interface ResponseModel<T> {
  success: boolean;
  status: string
  data: T
  error?: ExceptionResponseModel;
}
