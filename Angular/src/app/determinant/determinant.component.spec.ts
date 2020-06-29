import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { DeterminantComponent } from './determinant.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { DeterminantService } from './determinant.service';
import { Matrix } from '../domain/matrix';
import { of } from 'rxjs';

describe('DeterminantComponent', () => {
  let component: DeterminantComponent;
  let fixture: ComponentFixture<DeterminantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [{ provide: DeterminantService, useClass: ServiceStub }],
      imports: [ RouterTestingModule, HttpClientTestingModule, FormsModule ],
      declarations: [ DeterminantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeterminantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create a square matrix form based off of the dimension entered', () => {
    component.dimension = 2
    component.onSubmitDimension()
    expect(component.dimensionEstablished).toBeTrue()
    expect(component.form.length).toBe(2)
    expect(component.form.controls[0].value?.length).toBe(2)
  });

  it('should create a square matrix form based off of the dimension entered', () => {
    let injectedService = TestBed.get(DeterminantService);
    component.dimension = 2
    component.onSubmitDimension()
    component.form.controls[0].value[0] = 1
    component.form.controls[0].value[1] = 2

    component.form.controls[1].value[0] = 1
    component.form.controls[1].value[1] = 2

    let expectedMatrix = new Matrix()
    expectedMatrix.add_row([1,2])
    expectedMatrix.add_row([1,2])

    spyOn(injectedService, 'findDeterminant').and.callThrough();
    component.onSubmitDeterminant()
    expect(injectedService.findDeterminant).toHaveBeenCalledWith(expectedMatrix);
  });
});

export class ServiceStub{
  findDeterminant(matrix: Array<Matrix>){
    return of(4)
  }
}
