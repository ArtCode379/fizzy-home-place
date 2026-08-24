You are running the implementation phase for one Openclaw Android app.

Use these orchestrator instructions as binding context: /home/codex-agent/codex-app-agent/AGENTS.md
Use this screen spec: /home/codex-agent/codex-app-agent/screens-shop.md
Project directory: /tmp/fizzy-home-place

Task metadata:
- Asana task gid: 1217594401312985
- Asana task name: GB GW4 C1504
- Asana URL: https://app.asana.com/1/1208304498069546/project/1213586227413017/task/1217594401312985
- App name: Fizzy Home Place
- Company: THE FIZZY RASCAL LIMITED
- Domain: https://thefizzyrascal.casa
- Package: thefizzyrascal.household.fizzyhomeplace
- Prefix: FOTQV
- Type: shop
- Description: Специфика компании - розничная торговля товарами для дома: посуда, текстиль, декор и хозяйственные принадлежности. Приложение по продаже товаров компании, содержит список всех товаров компании (если разных категорий - с возможностью сортировки по категориям). История покупок. Корзина товаров с формой бронирования заказа. После подтверждения бронирования пользователь видит баннер с информацией о номере и деталях заказа с уведомлением о том, что его ожидают в магазине в течении 24 часов. Настройки должны содержать информацию о: названии компании, версии приложения, линку Customers Support со ссылкой сайт компании.  
(можно разнообразить главную страницу каруселью с заметками или статьями по уходу за домом)

Do Phase 2 and Phase 3 only:
1. Extract or derive the style guide.
2. Do not create project-local agent instruction files inside /tmp/fizzy-home-place.
3. Implement all required screens/content/data/assets/icon according to the orchestrator AGENTS.md and the screen spec.
4. Icon generation is best-effort: if Leonardo/imagegen cannot provide a filesystem-backed icon quickly, continue implementing the app with existing assets.
5. Do not push to GitHub, do not update Asana, and do not send Slack.
6. You may run local checks while implementing, but the runner will run quality/build afterward.
7. Keep every Kotlin file conventionally formatted: one statement per line, annotations above declarations, expanded indented Compose blocks, no semicolon-compressed code, and no source line longer than 200 characters.
