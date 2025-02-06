import {Component, Input, signal} from '@angular/core';
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {MatFormField, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {RouterOutlet} from "@angular/router";
import {AutocompleteComponent} from "../../shared/autocomplete/autocomplete.component";
import {DepartmentResponseModel} from "../../../../core/services/department/model/DepartmentResponse.model";
import {TrackResponseModel} from "../../../../core/services/track/model/TrackResponse.model";
import {SpecialityResponseModel} from "../../../../core/services/speciality/model/SpecialityResponse.model";
import {DepartmentService} from "../../../../core/services/department/department.service";
import {TrackService} from "../../../../core/services/track/track.service";
import {SpeciallityService} from "../../../../core/services/speciality/speciallity.service";

@Component({
  selector: 'app-study-plan-header',
  standalone: true,
  imports: [
    MatFormField,
    MatIcon,
    MatInput,
    MatSuffix,
    AutocompleteComponent,
    ReactiveFormsModule,
    RouterOutlet,
  ],
  templateUrl: './study-plan-header.component.html',
  styleUrl: './study-plan-header.component.scss'
})
export class StudyPlanHeaderComponent {

  departmentField = new FormControl<string | DepartmentResponseModel | null>('')
  allDepartmentResponse = signal<DepartmentResponseModel[]>([]);

  trackField = new FormControl<string | TrackResponseModel | null>('');
  allTrackResponse = signal<TrackResponseModel[]>([]);

  specialityField = new FormControl<string | SpecialityResponseModel | null>('');
  allSpecialityResponse = signal<SpecialityResponseModel[]>([]);

  @Input() searchField!: FormControl<string | null>

  constructor(
    private departmentService: DepartmentService,
    private trackService: TrackService,
    private specialityService: SpeciallityService
  ) {
  }

  ngOnInit() {
    this.findAllDepartments();
    this.findAllTracks()
    this.findAllSpeciality()

  }


  findAllDepartments(page?: number, size?: number) {
    this.departmentService.getAllDepartments(page, size).subscribe({
      next: (res) => {
        this.allDepartmentResponse.set(res.data?.content || [])
      },
      error: () => { /* Handle error */
      }
    });
  }

  findAllTracks(page?: number, size?: number) {
    this.trackService.getAllTracks(page, size).subscribe({
      next: (res) => {
        this.allTrackResponse.set(res.data?.content || [])
      },
      error: () => { /* Handle error */
      }
    });
  }

  findAllSpeciality(page?: number, size?: number) {
    this.specialityService.getAllSpeciality(page, size).subscribe({
      next: (res) => {
        this.allSpecialityResponse.set(res.data?.content || [])
      },
      error: () => { /* Handle error */
      }
    });
  }

  displayDepartment(department: DepartmentResponseModel): string {
    return department && department.name ? department.name : '';
  }

  displayTrack(track: TrackResponseModel): string {
    return track && track.name ? track.name : '';
  }

  displaySpeciality(speciality: SpecialityResponseModel): string {
    return speciality && speciality.name ? speciality.name : '';
  }
}
