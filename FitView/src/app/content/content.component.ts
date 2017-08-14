import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html'
})

export class ContentComponent implements OnInit {
  title = 'To jest content ...';

  ngOnInit() {
    this.title = "Nowy content ...";
    console.log(`ngOnInit`);
  }
}
