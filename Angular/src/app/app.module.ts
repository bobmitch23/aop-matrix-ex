import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DeterminantComponent } from './determinant/determinant.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MultiplyComponent } from './multiply/multiply.component';
import { CreateMatricesComponent } from './create-matrices/create-matrices.component';

@NgModule({
  declarations: [
    AppComponent,
    DeterminantComponent,
    MultiplyComponent,
    CreateMatricesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
