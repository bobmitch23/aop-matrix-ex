import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DeterminantService } from './determinant.service';
import { Matrix } from '../domain/matrix';
import { HttpClient } from '@angular/common/http';
import { GlobalConstants } from '../app-global.constants'

describe('DeterminantService', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let service: DeterminantService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [ HttpClientTestingModule ], providers: [DeterminantService]});
    service = TestBed.inject(DeterminantService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a matrix in a post request to the url', () => {
    const matrix = new Matrix()
    matrix.add_row([2,3])
    matrix.add_row([3,2])

    service.findDeterminant(matrix).toPromise();

    const req = httpTestingController.expectOne(GlobalConstants.url + '/findDeterminant');
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual(matrix);
  });
});
