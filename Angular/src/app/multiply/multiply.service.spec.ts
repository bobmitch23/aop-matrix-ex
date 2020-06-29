import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MultiplyService } from './multiply.service';
import { Matrix } from '../domain/matrix';
import { HttpClient } from '@angular/common/http';
import { GlobalConstants } from '../app-global.constants'

describe('MultiplyService', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let service: MultiplyService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [ HttpClientTestingModule ], providers: [MultiplyService]});
    service = TestBed.inject(MultiplyService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });


  afterEach(() => {
    httpTestingController.verify(); //Verifies that no requests are outstanding.
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send matrices in a post request to the url', () => {
    const matrices = new Array<Matrix>();
    const matrix1 = new Matrix()
    matrix1.add_row([2,3])
    matrix1.add_row([3,2])

    const matrix2 = new Matrix()
    matrix2.add_row([2,3])
    matrix2.add_row([3,2])

    matrices.push(matrix1)
    matrices.push(matrix2)

    service.multiplyMatrices(matrices).toPromise();

    const req = httpTestingController.expectOne(GlobalConstants.url + '/multiply');
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual(matrices);
  });
});
