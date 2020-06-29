import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateMatricesComponent } from './create-matrices.component';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

describe('CreateMatricesComponent', () => {
  let component: CreateMatricesComponent;
  let fixture: ComponentFixture<CreateMatricesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule, FormsModule ],
      declarations: [ CreateMatricesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMatricesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create proper number of forms based on number of matrices', () => {
     component.numMatrices = 4
     component.onSubmitNumMatrices()
     expect(component.form.length).toBe(4)
     expect(component.numMatricesEstablished).toBeTrue()
  });

  it('should show an error when the matrices dimensions are not valid', () => {
    component.numMatrices = 2
    component.onSubmitNumMatrices()
    
    component.form.controls[0].value[0] = 1
    component.form.controls[0].value[1] = 2

    component.form.controls[1].value[0] = 1
    component.form.controls[1].value[1] = 2

    component.onSubmitCreateMatrices()

    expect(component.error).toBeTrue()
 });

 it('should pass dimensions to the router when the dimensions are valid', () => {
  const routerstub: Router = TestBed.get(Router);
  spyOn(routerstub, 'navigate');

  component.numMatrices = 2
  component.onSubmitNumMatrices()
  
  component.form.controls[0].value[0] = 1
  component.form.controls[0].value[1] = 2

  component.form.controls[1].value[0] = 2
  component.form.controls[1].value[1] = 1

  component.onSubmitCreateMatrices()

  expect(component.error).toBeFalse()

  expect(routerstub.navigate).toHaveBeenCalledWith(['/multiply', [[1,2],[2,1]]]);
});

});
