import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from "src/app/app.component";
import { MenuComponent } from "src/app/menu/menu/menu.component";
import { RoomComponent } from "src/app/room/room.component";
import { ClientComponent } from './client/client.component';
import { ReservationComponent } from './reservation/reservation.component';


const routes: Routes = [
  {path: '', component: MenuComponent},
  {path: 'room', component: RoomComponent},
  {path: 'client', component: ClientComponent},
  {path: 'reservation', component: ReservationComponent},
  ];




@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash : true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
