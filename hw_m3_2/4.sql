ALTER TABLE project ADD cost NUMERIC;

UPDATE project
SET project.cost = (
    SELECT SUM(salary)
    FROM developers
    WHERE developers.id IN (
        SELECT PS.developer_id
        FROM projects_developers AS PS
        WHERE PS.project_id=project.id
    )
);

SELECT *
FROM project ;