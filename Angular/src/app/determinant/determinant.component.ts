import { Component, OnInit } from '@angular/core';
import { DeterminantService } from './determinant.service'
import { FormArray, FormControl } from '@angular/forms'
import { Matrix } from '../domain/matrix'
import { Observable } from 'rxjs';
import { Router, NavigationEnd} from '@angular/router';

@Component({
  selector: 'app-determinant',
  templateUrl: './determinant.component.html',
  styleUrls: ['./determinant.component.css']
})
export class DeterminantComponent implements OnInit {
  dimension : number
  dimensionEstablished : boolean
  solutionFound: boolean
  solution : Observable<number>
  form=new FormArray([]);

  constructor(private determinantService: DeterminantService, private router: Router) { 

    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    }

    this.router.events.subscribe((evt) => {
      if (evt instanceof NavigationEnd) {
        this.router.navigated = false;
        window.scrollTo(0, 0);
      }
    });
  }

  ngOnInit(): void {
    this.dimensionEstablished = false
    this.solutionFound = false
  }

  onSubmitDimension() {
     this.dimensionEstablished = true
     for (let i = 0; i < this.dimension; i++) {
      this.form.push(new FormArray([]))
      for (let j = 0; j < this.dimension; j++) {
        (this.form.at(i) as FormArray).push(new FormControl())
      }
    }
  }

  onSubmitDeterminant(){
    let matrix: Matrix = new Matrix()
    for(let i = 0; i < this.form.length; i++){
      let row : number[] = new Array<number>()
      for(let j = 0; j < this.form.length; j++){
        row.push(this.form.controls[i].value[j])
      }
      matrix.add_row(row)
    }

    this.solution = this.determinantService.findDeterminant(matrix)
  }

}
