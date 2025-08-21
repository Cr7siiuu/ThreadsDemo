import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { TabComponent } from './tab/tab.component';
import { NgModule } from '@angular/core';


export const routes: Routes = [
    {path:'',component:HomeComponent },
    {path:'tab',component : TabComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoute{}
