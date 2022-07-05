SELECT id, name, description
FROM project
WHERE id IN (
    SELECT project_id
    FROM projects_developers AS PD
    INNER JOIN developers ON PD.developer_id=developers .id
    GROUP BY project_id
    HAVING sum(salary) IN (
        SELECT sum(salary) costs
        FROM projects_developers AS PD
        INNER JOIN developers ON PD.developer_id=developers .id
        GROUP BY project_id
        ORDER BY costs DESC
    )
);