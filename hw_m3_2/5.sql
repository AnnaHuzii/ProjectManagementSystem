SELECT *
FROM project
WHERE cost = (SELECT MIN(cost) from project);