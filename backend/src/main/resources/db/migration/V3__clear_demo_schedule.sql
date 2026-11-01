-- Removes demo rows if V2__seed_schedule was applied before it was dropped from the repo.
DELETE FROM events WHERE id IN ('1', '2', '3', '4');
DELETE FROM home_highlights WHERE singleton_id = 1;
