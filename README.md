# TimeChecker
Checks transportation time tables, giving ones you can ride given current day and time.

# TODO:
- scrap only once per day, save result in local file and only fetch from website if file is too old.
- if website download fail, failback to last file
- add time left on each row
- add settings
  - timezone
  - transportation line to include + the relevant website to scrap
- add a list of options to choose from
- add button to show all data (sometimes we may need to check times of other days)
  - or maybe show everything, but only highlight the upcoming one? But this would make the time left display difficult
- add a link to the scrap website and jump to webbroser?
