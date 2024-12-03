import os

with open(".github/CHANGELOG.md", "r")as f:
    data = f.read().splitlines()

release_notes = []
seen_rls = False
for d in data:
    if d.startswith("#") and seen_rls:
        break
    if d.startswith("#") and not seen_rls:
        seen_rls = True
    elif d:
        if d.split(".")[1].startswith(" [Game]") and not d.split(".")[1].startswith(" [Game] Test"):
            release_notes.append(" ".join(d.split(".")[1].split("-")[1:]))

print("\n".join(release_notes))

with open(os.environ['GITHUB_OUTPUT'], 'a')as fh:
    print(f"release_notes={'\n'.join(release_notes)}", file=fh)
