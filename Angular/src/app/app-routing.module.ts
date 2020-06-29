import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeterminantComponent } from './determinant/determinant.component';
import { MultiplyComponent } from './multiply/multiply.component';
import { CreateMatricesComponent } from './create-matrices/create-matrices.component';
 
const routes: Routes = [
  { path: 'determinant', component: DeterminantComponent },
  { path: 'multiply', component: MultiplyComponent },
  { path: 'create-matrices', component: CreateMatricesComponent }
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }