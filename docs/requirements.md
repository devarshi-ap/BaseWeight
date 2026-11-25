# BaseWeight -- Requirements Specification

Nov 24 2026 · Dev Patel

## Overview

A tool to coordinate trip details and gear in group backpacking trips and to log/track/optimize your own pack’s weight & gear

Priority key: **M** = Must, **S** = Should, **C** = Could (MoSCoW).

## Problem Statement

Problems:

1. **P1: Not knowing what you need.** On my first trip I had to google an REI checklist to learn that a sleep system was even a thing. Organizing a backpacking locker into *systems*, with empty slots for anything you don’t have, teaches beginners the checklist without them having to look for it.
2. **P2: Small things getting forgotten.** Easy to forget batteries, utensils and soap, and that can cost on the hike. A saved loadout you tick off item by item catches the small stuff that slips through when you pack from memory.
3. **P3: Rebuilding the list from scratch every trip.** You spend hours packing the night before. If your gear is logged once and your loadouts are saved, the next trip is a quick review instead of a full inventory.
4. **P4: Gear lists being hard to read and keep up to date.** Spreadsheets are hard on the eyes and nobody updates them. That’s why focus on the UI matters so much.
5. **P5: Not knowing what you’re carrying.** You can see your total weight and what’s heaviest, so you can cut weight before you’re on the trail.

## User stories

I've listed 15 stories (9-Must + 5-Should + 1-Could) listed across 6 groups (Account, Logging, Planning, Optimize, Coverage, Packing)

| ID | Priority | User Story | Acceptance Criteria |
|---|---|---|---|
| ACC-01 | M | As a new user, I want to sign up with email or Google so my gear is saved. | User can sign up, log in and log out. Their session stays logged in between visits. |
| LOG-01 | M | As a user, I want to add gear with a name, system, category, weight, and quantity so my locker reflects what I own. | Name, system and weight are required. Quantity is default 1. Weight is stored in one unit internally. The item appears under its system right away. |
| LOG-02 | M | As a user, I want to edit (name/category/weight/quantity) or delete gear so my locker stays accurate. | Edits show up in every loadout that uses the item. Deleting asks for confirmation and warns if the item is in a loadout. |
| LOG-03 | M | As a user, I want my locker grouped by system so it's easy to scan, not a spreadsheet. | Systems can be collapsed. Each system shows its item count and total weight. Empty systems still appear. |
| LOG-04 | S | As a user, I want to add an optional photo so I can recognize items at a glance. | Photo is optional. JPG, PNG and HEIC are accepted under a size limit. A thumbnail shows in the list. |
| LOG-05 | S | As a user, I want to choose lb/oz or g/kg so weights make sense to me. | All weights display in the chosen unit. Switching units doesn't change stored values. |
| PLAN-01 | M | As a user, I want to create a named loadout from my locker so I don't rebuild my list every trip. | Name is required. I pick items from my locker, grouped by system. The loadout is saved and can be reopened. |
| PLAN-02 | M | As a user, I want to see my total loadout weight, updating instantly as items change. | 1 total weight (base+worn+consumable tags later). They update instantly when items change. |
| PLAN-03 | M | As a user, I want to set a target weight and see a progress bar (e.g. 31.4 / 35 lb) so I know if I'm over. | The target is optional. The bar turns to a warning state when my total weight (base+worn+consumable) is over target. |
| PLAN-04 | S | As a user, I want to duplicate a loadout so "Shoulder season" can start from "3-season". | Creates a copy named "(copy)". Editing the copy doesn't change the original. |
| OPT-01 | M | As a user, I want to see weight by system so I can see where the weight is. | Each system shows its weight and % of total weight. |
| COV-01 | M | As a beginner, I want empty slots for each system (e.g. "Sleeping pad: none") so I learn what I'm missing. | Each system has preset slots marked core or optional. Missing core slots are clearly flagged. |
| COV-02 | S | As a user, I want to tap an empty slot to add that gear so filling gaps is quick. | Opens the add-gear form with the system and category already filled in. |
| COV-03 | C | As a user, I want to dismiss a slot I don't need (e.g. pillow) so coverage stays relevant. | Dismissing applies per loadout and can be undone. |
| PACK-01 | S | As a user, I want to tick off each item while packing so the small stuff doesn't get forgotten. | A checkbox for each item and an X/Y progress count. Progress is kept if the app closes. There's a "reset for next trip" option. |
| GRP-01 | Won't (v1) | As a user, I want to create or join trips and see others' setups. | Planned for Phase 2. The data model should support it now. |
| GRP-02 | Won't (v1) | As a group, we want shared-gear and responsibility splitting. | Later phase. |

## Backpacking Systems

| # | System | Core slots ★ | Optional slots |
|---|---|---|---|
| 1 | Pack | Backpack | Rain cover/pack liner, stuff sacks |
| 2 | Cook | Stove, fuel, pot, utensil, lighter/matches | Mug, bear canister/food bag |
| 3 | Water | Filter/purifier, bottles/bladder | — |
| 4 | Clothing & Footwear | Rain jacket, insulating layer, extra socks | Hiking boots/shoes (worn), base layers, hat/gloves, camp shoes |
| 5 | Shelter | Tent/tarp/hammock, stakes | Footprint, guylines |
| 6 | Sleep | Sleeping bag/quilt, sleeping pad | Pillow, liner |
| 7 | Navigation | Map/GPS app | Compass, permit |
| 8 | Lighting & Power | Headlamp | Batteries, power bank, cables |
| 9 | Hygiene & First Aid | First aid kit, trowel, toilet paper | Soap, sunscreen, bug spray, toothbrush |
| 10 | Tools & Repair | — | Knife/multitool, repair tape, trekking poles |
| 11 | Other | — | No preset slots (catch-all for anything that doesn't fit above) |

## Functional requirements

I've listed 18 system-level requirements, each traced to the story it fulfils. Stories say what the visitor needs; FRs say what the system must do to deliver it.

| FR | Story | Priority | Requirement |
|---|---|---|---|
| FR-01 | ACC-01 | M | The system shall let users sign up and log in with email/password or Google. |
| FR-02 | ACC-01 | M | The system shall keep users logged in across visits until they log out or the session expires. |
| FR-03 | ACC-01 | M | The system shall restrict each user's gear and loadouts so only that user can view or change them. |
| FR-04 | LOG-01 | M | The system shall let users add a gear item with name (required), system (required, from the predefined list), category, unit weight (required, > 0) and quantity (whole number ≥ 1, default 1). |
| FR-05 | LOG-01 | M | The system shall store weights in grams and calculate item weight as unit weight × quantity. |
| FR-06 | LOG-02 | M | The system shall let users edit any field of a gear item, and the change shall show up in every loadout containing it. |
| FR-07 | LOG-02 | M | The system shall ask for confirmation before deleting an item, list any loadouts that use it, and remove it from those loadouts once deleted. |
| FR-08 | LOG-03 | M | The system shall show the locker grouped by system in necessity order, including systems with no items. |
| FR-09 | LOG-03 | M | The system shall show each system's item count and total weight, and let users collapse or expand it. |
| FR-10 | LOG-04 | S | The system shall let users add, replace or remove one optional photo per item (JPG/PNG/HEIC, ≤ 5 MB) and show it as a thumbnail. |
| FR-11 | LOG-05 | S | The system shall let users choose imperial (lb/oz) or metric (g/kg) and use that unit everywhere weights are entered or shown, without changing stored values. |
| FR-12 | PLAN-01 | M | The system shall let users create a named loadout by selecting items from their locker, grouped by system. |
| FR-13 | PLAN-01 | M | The system shall let users list, rename and delete loadouts, and add or remove items in them. Deleting a loadout shall not delete any gear. |
| FR-14 | PLAN-02 | M | The system shall show a loadout's total weight and recalculate it immediately when items or quantities change. |
| FR-15 | PLAN-03 | M | The system shall let users set an optional target weight per loadout, show a current/target progress bar, and show a warning state when over target. |
| FR-16 | PLAN-04 | S | The system shall let users duplicate a loadout as "<name> (copy)", where later edits to either one don't affect the other. |
| FR-17 | OPT-01 | M | The system shall show each system's weight and its % of the loadout total, sorted heaviest first. |
| FR-18 | COV-01 | M | The system shall keep slot templates for each system in a config file, with each slot marked core or optional. |
| FR-19 | COV-01 | M | The system shall show every slot in a loadout as filled or empty, based on whether an item's category matches the slot. |
| FR-20 | COV-01 | M | The system shall flag empty core slots, with urgency based on the system's necessity order. |
| FR-21 | COV-02 | S | Tapping an empty slot shall open the add-gear form with the system and category already filled in, and saving shall add the item to both the locker and the loadout. |
| FR-22 | COV-03 | C | The system shall let users dismiss a slot for one loadout and undo the dismissal later. |
| FR-23 | PACK-01 | S | The system shall offer a checklist mode for a loadout, with a checkbox per item (showing ×N), an X/Y progress count, progress saved to the account, and a reset option. |

## Non-functional requirements

TBD

## Scope, assumptions, constraints, and risks

**Out of scope (W in MoSCoW):** Creating/Joining Group Trips

**Assumptions**
- TBD

**Constraints**
- TBD

**Risks**
- TBD