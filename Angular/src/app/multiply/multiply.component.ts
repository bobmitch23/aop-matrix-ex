import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MultiplyService } from './multiply.service'
import { Matrix } from '../domain/matrix'
import { FormArray, FormControl } from '@angular/forms'

@Component({
  selector: 'app-multiply',
  templateUrl: './multiply.component.html',
  styleUrls: ['./multiply.component.css']
})
export class MultiplyComponent implements OnInit {
  formArray=new Array<FormArray>();
  solutionValues : number[][]


  constructor(private route: ActivatedRoute, private service: MultiplyService) { 
    this.route.paramMap.subscribe(paramMap => {
      paramMap.keys.forEach(key => {
        let form=new FormArray([]);
        for (let i = 0; i < +paramMap.get(key)[0]; i++) {
          form.push(new FormArray([]))
          for (let j = 0; j < +paramMap.get(key)[2]; j++) {
            (form.at(i) as FormArray).push(new FormControl())
          }
        }
        this.formArray.push(form)
      })
    })
  }

  ngOnInit(): void {
  }

  multiply(){
    let matrices = new Array<Matrix>()
    this.formArray.forEach(form =>{
      let matrix: Matrix = new Matrix()
      for(let i = 0; i < form.length; i++){
        let row : number[] = new Array<number>()
        for(let j = 0; j < form.controls[i].value?.length; j++){
          row.push(form.controls[i].value[j])
        }
        matrix.add_row(row)
      }
      matrices.push(matrix)
    })
    this.service.multiplyMatrices(matrices).subscribe(matrix => {
      this.solutionValues = matrix.values
    })
  }

}
