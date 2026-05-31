import os
import re
import json

base_dir = r"c:\Users\hp\AndroidStudioProjects\skillsyncaiapp\app\src\main\java\com\simats\SkillSyncAI"

# Regex patterns
top_app_bar_pattern = re.compile(r'TopAppBar\s*\(\s*title\s*=\s*\{\s*Text\s*\(\s*"([^"]+)"')
fallback_title_pattern = re.compile(r'Text\(\s*text\s*=\s*"([^"]+)"\s*,\s*style\s*=\s*MaterialTheme\.typography\.displaySmall')
desc_pattern = re.compile(r'Text\(\s*text\s*=\s*"([^"]+)"\s*,\s*style\s*=\s*MaterialTheme\.typography\.body(?:Medium|Large)')
card_pattern = re.compile(r'RoadmapCard\s*\(\s*title\s*=\s*"([^"]+)"\s*,\s*items\s*=\s*listOf\s*\((.*?)\)', re.DOTALL)

def extract_strings_from_list(list_str):
    return [s.strip() for s in re.findall(r'"([^"]+)"', list_str)]

def clean_string(s):
    return s.replace('\\n', ' ').replace('\n', ' ').strip()

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    if 'RoadmapCard' not in content:
        return None

    m_title = top_app_bar_pattern.search(content)
    if m_title:
        title = m_title.group(1)
    else:
        m_title2 = fallback_title_pattern.search(content)
        title = m_title2.group(1) if m_title2 else os.path.basename(filepath).replace('.kt', '')

    subtitle_m = fallback_title_pattern.search(content)
    subtitle = subtitle_m.group(1) if subtitle_m else title

    desc_m = desc_pattern.search(content)
    description = desc_m.group(1) if desc_m else ""

    cards = []
    for match in card_pattern.finditer(content):
        card_title = clean_string(match.group(1))
        items_str = match.group(2)
        items = extract_strings_from_list(items_str)
        cards.append({
            "title": card_title,
            "items": items
        })

    clean_title = clean_string(title)
    clean_subtitle = clean_string(subtitle)
    clean_desc = clean_string(description)

    # Make a clean slug
    # Remove common suffixes and lowercase
    slug = clean_title.lower().replace(" roadmap", "").replace("mastery path", "").replace("roadmap", "").replace("mastery", "").replace("screen", "").strip()
    slug = re.sub(r'[^a-z0-9]+', '-', slug).strip('-')

    return {
        "slug": slug,
        "title": clean_title,
        "subtitle": clean_subtitle,
        "description": clean_desc,
        "sections": cards
    }

roadmaps = []
for filename in os.listdir(base_dir):
    if filename.endswith("Screen.kt"):
        filepath = os.path.join(base_dir, filename)
        data = process_file(filepath)
        if data:
            roadmaps.append(data)

out_path = r"c:\Users\hp\AndroidStudioProjects\skillsyncaiapp\webapp\src\data\roadmaps.json"
os.makedirs(os.path.dirname(out_path), exist_ok=True)
with open(out_path, 'w', encoding='utf-8') as f:
    json.dump(roadmaps, f, indent=2)

print(f"Extracted {len(roadmaps)} roadmaps to {out_path}")
