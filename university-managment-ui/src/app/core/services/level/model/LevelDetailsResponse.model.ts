export class LevelDetailsResponseModel {
  constructor(id?: number,
              name?: string,
              description?: string,
              department?: string,
              specialityWithTrack?: string) {

  }
}

export class FilterLevelParamModel {
  constructor(specialityLike?: string,
              trackLike?: string,
              departmentLike?: string) {
  }
}

