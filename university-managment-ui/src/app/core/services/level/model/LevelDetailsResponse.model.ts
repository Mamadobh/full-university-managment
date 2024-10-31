export interface LevelDetailsResponseModel {
  id?: number;
  name?: string;
  description?: string;
  department?: string;
  specialityWithTrack?: string;
}

export class FilterLevelParamModel {
  [key: string]: string | undefined;

  constructor(specialityLike?: string,
              trackLike?: string,
              departmentLike?: string) {
  }
}


export class LevelRequest {

  constructor(
    name: string,
    description: string,
    specialityId: number,
    id?: number,
  ) {
  }


}

export class LevelResponse {

  constructor( public id?: number,
               public name?: string,
               public description?: string,
               public specialityId?: number,
               public studyPlan?: Uint8Array) {
  }


}

