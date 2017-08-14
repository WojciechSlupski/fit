import { FitViewPage } from './app.po';

describe('fit-view App', () => {
  let page: FitViewPage;

  beforeEach(() => {
    page = new FitViewPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
