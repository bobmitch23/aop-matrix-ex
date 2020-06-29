import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { MultiplyComponent } from './multiply.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Matrix } from '../domain/matrix';
import { of } from 'rxjs';
import { convertToParamMap } from '@angular/Router'
import { MultiplyService } from './multiply.service';

describe('MultiplyComponent', () => {
  let component: MultiplyComponent;
  let fixture: ComponentFixture<MultiplyComponent>;
  let injectedService : MultiplyService

  const mockActivatedRoute = { 
    paramMap: of(convertToParamMap({ "0": "1,2", "1": "2,1" })) 
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [{ provide: ActivatedRoute, useValue: mockActivatedRoute}, { provide: MultiplyService, useClass: ServiceStub }],
      imports: [ HttpClientTestingModule, FormsModule, ReactiveFormsModule ],
      declarations: [ MultiplyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should populate the form array based off of route params', () => {
    //2 matrices
    expect(component.formArray.length).toBe(2);
    
    //matrix 1, 1 row 2 cols
    expect(component.formArray[0].controls.length).toBe(1);
    expect(component.formArray[0].controls[0].value?.length).toBe(2);

    //matrix 2, 2 rows, 1 col
    expect(component.formArray[1].controls.length).toBe(2);
    expect(component.formArray[1].controls[0].value?.length).toBe(1);
  });

  it('should call the service with matrices constructed from the form data', () => {
    injectedService = TestBed.get(MultiplyService);

    component.formArray[0].controls[0].value[0] = 1
    component.formArray[0].controls[0].value[1] = 2

    component.formArray[1].controls[0].value[0] = 1
    component.formArray[1].controls[1].value[0] = 2

    let expectedMatrix1 = new Matrix()
    expectedMatrix1.add_row([1,2])
    let expectedMatrix2 = new Matrix()
    expectedMatrix2.add_row([1])
    expectedMatrix2.add_row([2])

    spyOn(injectedService, 'multiplyMatrices').and.callThrough();
    component.multiply()
    expect(injectedService.multiplyMatrices).toHaveBeenCalledWith([expectedMatrix1, expectedMatrix2]);
  });
});

export class ServiceStub{
  multiplyMatrices(matrices: Array<Matrix>){
    return of({
      values: [[1,2]]
    })
  }
}
