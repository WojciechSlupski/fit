import { Component } from '@angular/core';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html'
})
export class ContentComponent {
  title = 'To jest content ...';

  ngOnInit() {
    this.title = "Nowy content ...";
    console.log(`ngOnInit`);
  }
}
