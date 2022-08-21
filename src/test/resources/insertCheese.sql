insert into cheese(name, vegetarian, countryId, colourId, likes, dislikes, version)
values ('test', true, (select id from countries where name = 'test'),
        (select id from colours where name = 'test'), 1, 2, 3);
insert into webpages(cheeseId, url)
values ((select id from cheese where name = 'test'), 'testUrl');