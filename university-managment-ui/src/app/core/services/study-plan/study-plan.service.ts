import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_PATH} from "../../Constants";
import {Observable} from "rxjs";
import {ResponseModel} from "../../model/Response.model";
import {PageResponseModel} from "../../model/PageResponse.model";
import {SpecialityResponseModel} from "../speciality/model/SpecialityResponse.model";
import {LevelDetailsResponseModel} from "../level/model/LevelDetailsResponse.model";

@Injectable({
  providedIn: 'root'
})
export class StudyPlanService {

}
