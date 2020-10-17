import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EbookComponent } from './ebook/ebook.component';

const routes: Routes = [
	{path: 'ebooks', component: EbookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
