import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd} from '@angular/router';
import { FormArray, FormControl } from '@angular/forms'

@Component({
  selector: 'app-create-matrices',
  templateUrl: './create-matrices.component.html',
  styleUrls: ['./create-matrices.component.css']
})
export class CreateMatricesComponent implements OnInit {
  numMatrices : number
  numMatricesEstablished : boolean
  form=new FormArray([]);
  error : boolean

  constructor(private router: Router) { 
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
    this.numMatricesEstablished = false
    this.error = false
  }

  onSubmitNumMatrices() {
    this.numMatricesEstablished = true
    for (let i = 0; i < this.numMatrices; i++) {
      let formArray: FormArray = new FormArray([])
      formArray.push(new FormControl())//row
      formArray.push(new FormControl())//column
      this.form.push(formArray)
    }
  }
  
  onSubmitCreateMatrices() {
    let dimensions:number[][] = new Array<Array<number>>()
    for(let i = 0; i < this.form.length; i++){
      let rowXcol = new Array<number>()
      rowXcol.push(this.form.controls[i].value[0])
      rowXcol.push(this.form.controls[i].value[1])
      if(i > 0 && this.form.controls[i-1].value[1] != this.form.controls[i].value[0]){
        this.error = true
        setTimeout(() => {
          this.error = false;
        }, 3000)
      }
      dimensions.push(rowXcol)
    }
    if(!this.error){
      this.router.navigate(['/multiply', dimensions]);
    }
  }

}
