SELECT SUM(salary)
FROM developers
WHERE id in (
    SELECT DEVELOPER_ID
    FROM developers_skills AS DS
    INNER JOIN skills ON DS.skill_id=skills.id
    WHERE INDUSTRY ='Java'
);