import {Component, OnInit, signal} from '@angular/core';
import {StudyPlanHeaderComponent} from "../../../components/study-plan/study-plan-header/study-plan-header.component";
import {SimpleCartComponent} from "../../../components/shared/simple-cart/simple-cart.component";
import {PaginationComponent} from "../../../components/shared/pagination/pagination.component";
import {LevelService} from "../../../core/services/level/level.service";
import {LevelDetailsResponseModel} from "../../../core/services/level/model/LevelDetailsResponse.model";
import {PageResponseModel} from "../../../core/model/PageResponse.model";
import {MatIcon} from "@angular/material/icon";
import {FormControl} from "@angular/forms";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";


@Component({
  selector: 'app-study-plan',
  standalone: true,
  imports: [
    StudyPlanHeaderComponent,
    SimpleCartComponent,
    PaginationComponent,
    MatIcon,
    PageHeaderComponent,

  ],
  templateUrl: './study-plan.component.html',
  styleUrl: './study-plan.component.scss',
})
export class StudyPlanComponent implements OnInit {
  linkHistory: { label: string, navLink: string }[] = [{label: "study plan", navLink: "/study-plan"}]
  allLevelResponse = signal<PageResponseModel<LevelDetailsResponseModel>>(new PageResponseModel())
  currentPage: number = 0;
  currentSize: number = 1
  pageInterval: { start: number, end: number } = {
    start: 1, end: 3
  }
  searchfieldControl = new FormControl<string>('')
  filtredData = signal<LevelDetailsResponseModel[]>(this.allLevelResponse().content)

  constructor(private levelService: LevelService) {
  }

  ngOnInit() {

    this.searchfieldControl.valueChanges.subscribe((data) => {
      this.search(data as string)
    })
    this.levelService.paramSubject.subscribe((data) => {
      this.levelService.filterParam = data;
      this.findAllLevel(this.currentPage, this.currentSize)
    })
    this.findAllLevel(this.currentPage, this.currentSize)
  }

  onNextPage(page: number) {
    this.currentPage = page
    this.pageInterval = this.getPageInterval()
    this.findAllLevel(this.currentPage, this.currentSize)
  }

  onPreviousPage(page: number) {
    this.currentPage = page
    this.pageInterval = this.getPageInterval()
    this.findAllLevel(this.currentPage, this.currentSize)
  }

  onTargetPage(target: number) {
    this.currentPage = target
    this.findAllLevel(this.currentPage, this.currentSize)
  }

  onPageSizeChange(size: number) {
    this.currentSize = size
    if (this.currentPage > (Math.ceil(this.allLevelResponse().totalElments / size) - 1)) {
      this.currentPage = Math.ceil(this.allLevelResponse().totalElments / size) - 1
      this.pageInterval = this.getPageInterval()
    }
    this.findAllLevel(this.currentPage, this.currentSize)
  }


  findAllLevel(page?: number, size?: number) {
    console.log("d5al")
    this.levelService.getAllLevelWithDetails(page, size).subscribe({
      next: (res) => {
        this.allLevelResponse.set(res.data)
        this.searchfieldControl.setValue("")
        console.log("data=      ", this.allLevelResponse());
      },
      error: () => { /* Handle error */
      }
    });
  }


  private getPageInterval(): { start: number, end: number } {

    return {
      start: Math.floor(this.currentPage / 3) * 3 + 1,
      end: Math.floor(this.currentPage / 3) * 3 + 3
    }
  }

  private search(value: string) {
    const filtered = this.allLevelResponse()
      .content.filter((el) => this.objectSearch(el, value));
    this.filtredData.set(filtered);
  }

  private objectSearch(object: Record<string, any>, val: string): boolean {
    const searchValue = val.toLowerCase();
    return Object.keys(object).some((key) => {
      const element = object[key];
      if (typeof element === 'string') {
        return element.toLowerCase().includes(searchValue);
      } else if (typeof element === 'number' || typeof element === 'boolean') {
        return element.toString().includes(searchValue);
      } else if (element && typeof element === 'object') {
        return this.objectSearch(element, searchValue);
      }
      return false;
    });
  }
}
