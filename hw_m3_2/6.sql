SELECT name, ROUND(cost/(
    SELECT count(developer_id)
    FROM projects_developers
    WHERE project_id=id
) )AS average_salary
FROM project
WHERE id IN (
SELECT id
    FROM project
    WHERE cost IN (
        SELECT cost
        FROM project
        WHERE cost = (SELECT MIN(cost) from project)
    )
);
