# Gamification

Kata created by me for a gamification app

## Description

You received a job to create a daily check-in challenge to integrate with the gamification area of the company.

The idea is to create a cli app so the users can do the check-ins on a daily basis and receive the points for it. 

### Rules

- A user that never have done a check-in should be considered as the first
- A user cannot make another check-in within the same day
- A user can only make two check-ins in two different days
- The streak only increases as long the user keep making the check-ins day after
- If the user stop for two days and make a check-in on the third day, it will restart the count
- The check-ins streak only last for 7 days, in the 8th check-in it will be considered a reset